import { NgModule } from '@angular/core';
import { SignInComponent } from '../view/sign-in/sign-in.component';
import { SignOutComponent } from '../view/sign-out/sign-out.component';
import { NotAuthorizedComponent } from '../view/not-authorized/not-authorized.component';
import { AuthRoutingModule } from './auth-routing.module';

@NgModule({
  declarations: [
    SignInComponent,
    SignOutComponent,
    NotAuthorizedComponent
  ],
  imports: [
    AuthRoutingModule
  ],
  providers: [ ]
})
export class AuthModule {}
