import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable, } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private apiUrl = 'http://localhost:8080/rewards/';

  constructor(private http: HttpClient) { }

  getTransactions(): Observable<any> {
    return this.http.get(`${this.apiUrl}transactions`);
  }

  getRewardPoints(): Observable<any> {
    return this.http.get(`${this.apiUrl}calculate`);
  }

  addMonthlyTransaction(transactionData: any): Observable<any> {
    return this.http.post(`${this.apiUrl}calculate`, transactionData);
  }
  deleteAllTransactions(): Observable<void> {
    const url = `${this.apiUrl}clear`; // Adjust the URL as needed
    return this.http.delete(url, { responseType: 'text' }).pipe(map(() => undefined));
  }
  deleteTransaction(transactionId: number): Observable<void> {
    const url = `${this.apiUrl}deleteByTransactionId`; // Adjust the URL as needed
    const params = new HttpParams().set('transactionId', transactionId.toString());
    
    return this.http.delete(url, { params, responseType: 'text' }).pipe(
      map(() => undefined)
    );
  }
  
}
