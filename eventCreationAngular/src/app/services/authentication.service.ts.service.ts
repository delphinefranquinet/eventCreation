import { Injectable } from '@angular/core';

@Injectable()
export class AuthenticationService {

constructor() { }

public isConnected(): boolean {
  return !!localStorage.getItem('auth');
}
}
