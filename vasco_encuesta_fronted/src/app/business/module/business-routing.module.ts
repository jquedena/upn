import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonaComponent } from '../view/persona/persona.component';
import { EncuestaComponent } from '../view/encuesta/encuesta.component';
import { LayoutComponent } from '../view/layout/layout.component';

const businessRoutes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {path: 'persona', component: PersonaComponent},
      {path: 'encuesta', component: EncuestaComponent}
    ]
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(businessRoutes)
  ],
  exports: [
    RouterModule
  ],
  providers: []
})
export class BusinessRoutingModule {}
