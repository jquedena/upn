import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PersonComponent } from '../view/person/person.component';
import { QuizComponent } from '../view/quiz/quiz.component';
import { LayoutComponent } from '../view/layout/layout.component';

const businessRoutes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {path: 'persona', component: PersonComponent},
      {path: 'encuesta', component: QuizComponent}
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
