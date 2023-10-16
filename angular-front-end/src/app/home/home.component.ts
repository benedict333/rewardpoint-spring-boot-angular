import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DataService } from '../data.service';
import { RewardPointsData } from './models/model';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { Router } from '@angular/router';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [
    trigger('expandCollapse', [
      state('collapsed', style({ height: '0', opacity: 0 })),
      state('expanded', style({ height: '*', opacity: 1 })),
      transition('collapsed => expanded', animate('300ms ease-in-out')),
      transition('expanded => collapsed', animate('300ms ease-in-out')),
    ]),
  ],
})
export class HomeComponent implements OnInit {
  @ViewChild('addTransactionForm') addTransactionFormElement!: ElementRef;

  showAddTransactionSection = false;
  animationState = 'collapsed';

  selectedTab = 'transactions';
  pastTransactions: any[] = [];
  rewardPoints: RewardPointsData | null = null;
  addTransactionForm: FormGroup;
  errorMessage: string = '';
  successMessage: string | null = null; // Initialize it as null


  constructor(private dataService: DataService, private fb: FormBuilder, private router: Router) {
    this.addTransactionForm = this.fb.group({
      customerId: [1],
      transactionAmount: [null, [Validators.required, Validators.min(0.01)]],
      transactionDate: [null, Validators.required],
    });
  }

  ngOnInit() {
    this.loadTransactions();
    this.loadRewardPoints();
    this.selectedTab = 'transactions';
  }

  loadTransactions() {
    this.dataService.getTransactions().subscribe(
      (data: any) => {
        this.pastTransactions = data;
      },
      (error) => {
        this.errorMessage = 'Error fetching past transactions. Please try again.';
      }
    );
  }

  loadRewardPoints() {
    this.dataService.getRewardPoints().subscribe(
      (data: RewardPointsData) => {
        this.rewardPoints = data;
      },
      (error) => {
        this.errorMessage = 'Error fetching reward points. Please try again.';
      }
    );
  }

  toggleAddTransactionSection() {
    this.showAddTransactionSection = !this.showAddTransactionSection;
    this.animationState = this.showAddTransactionSection ? 'expanded' : 'collapsed';
  }

  submitAddTransactionForm() {
    if (this.addTransactionForm.valid) {
      const formData = this.addTransactionForm.value;
      this.dataService.addMonthlyTransaction(formData).subscribe(
        (response: any) => {
          this.addTransactionForm.reset();
          this.showAddTransactionSection = false;
          this.loadTransactions();
          this.loadRewardPoints();
          
          // Set the transaction success message
          this.successMessage= 'Transaction added successfully.';
          this.errorMessage = ""; // Clear any existing error message
  
          // Clear the success message after a delay (e.g., 5 seconds)
          setTimeout(() => {
           this.successMessage = null;
          }, 5000);
        },
        (error) => {
          this.errorMessage = 'Error adding transaction. Please try again.';
        }
      );
    }
  }
  
  deleteTransaction(customerId: number) {
    if (confirm('Are you sure you want to delete this transaction? This action cannot be undone.')) {
      this.dataService.deleteTransaction(customerId).subscribe(
        () => {
          console.log('Transaction deleted successfully');
          this.loadTransactions();
          this.loadRewardPoints();
          this.successMessage = 'Transaction deleted successfully.';
          this.errorMessage = ''; // Clear any existing error message

          setTimeout(() => {
            this.successMessage = null;
          }, 5000);
        },
        (error) => {
          this.errorMessage = 'Error deleting transaction. Please try again.';
        }
      );
    }
  }

  resetData() {
    if (confirm('Are you sure you want to reset all data with pre-loaded transactions in the memory? All the data you added will be removed!')) {
      this.dataService.deleteAllTransactions().subscribe(
        (response: any) => {
          console.log('Response from resetData:', response);
          this.loadTransactions();
          this.loadRewardPoints();
          // Set the success message
          this.successMessage = 'All data cleared successfully.';
          this.errorMessage = ""; // Clear any existing error message
  
          // Clear the success message after a delay (e.g., 5 seconds)
          setTimeout(() => {
            this.successMessage = null;
          }, 5000);
        },
        (error) => {
          this.errorMessage = 'Error resetting data. Please try again.';
          console.error('Error resetting data:', error);
        }
      );
    }
  }
  
  
  
  
  
}
