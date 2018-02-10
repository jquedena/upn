import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { InicioSesionComponent } from './view/inicio-sesion/inicio-sesion.component';
import { EncuestaComponent } from './view/encuesta/encuesta.component';
import { PersonaComponent } from './view/persona/persona.component';
import { AuthGuard } from './service/auth-guard.service';

const routes: Routes = [
  { path: '', redirectTo: '/inicio-sesion', pathMatch: 'full' },
  { path: 'inicio-sesion', component: InicioSesionComponent, canActivate: [AuthGuard] },
  { path: 'encuesta', component: EncuestaComponent, canActivate: [AuthGuard] },
  { path: 'persona', component: PersonaComponent, canActivate: [AuthGuard] }
]

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
