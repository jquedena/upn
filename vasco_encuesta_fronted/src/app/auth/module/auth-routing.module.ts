import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from '../service/auth-guard.service';
import { AuthService } from '../service/auth.service';
import { SignInComponent } from '../view/sign-in/sign-in.component';
import { SignOutComponent } from '../view/sign-out/sign-out.component';
import { NotAuthorizedComponent } from '../view/not-authorized/not-authorized.component';

const authRoutes: Routes = [
  { path: 'sign-in', component: SignInComponent },
  { path: 'sign-out', component: SignOutComponent },
  { path: 'not-authorized', component: NotAuthorizedComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(authRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: [
    AuthGuard,
    AuthService
  ]
})
export class AuthRoutingModule {}
