import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service.ts.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router:Router , private authService:AuthenticationService){}//we have a direct access to Router like this
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    //const auth = !!localStorage.getItem('auth');
    const auth =  this.authService.isConnected();
    if(!auth) {
      this.router.navigate(['/creatEvent']);
    }
    return !!auth;//if it false we don't have access
    //that !!auth is the equivalent of
    //if(auth){return true;}else{return false;}
    //return auth ? true : false;
  }

}
