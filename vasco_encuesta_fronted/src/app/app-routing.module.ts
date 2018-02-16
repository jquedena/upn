import { NgModule } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import { AuthGuard } from './auth/service/auth-guard.service';
import { Error404Component } from './support/view/error404/error404.component';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full' },
  { path: '', loadChildren: 'app/auth/module/auth.module#AuthModule' },
  { path: 'app', 
    loadChildren: 'app/business/module/business.module#BusinessModule', 
    canLoad: [ AuthGuard ]
  },
  { path: '404', component: Error404Component },
  { path: '**', redirectTo: '/404' }
]

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {

  // Diagnostic only: inspect router configuration
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
}
