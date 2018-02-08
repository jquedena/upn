import { MatInputModule, MatFormField, MatFormFieldModule, MatIconModule } from '@angular/material';
import { ModuleWithProviders, NgModule } from '@angular/core';

@NgModule({
    imports: [
        MatFormFieldModule,
        MatInputModule,
        MatIconModule
    ],
    exports: [
        MatFormFieldModule,
        MatInputModule,
        MatIconModule
    ],
})

export class MaterialModule {
    static forRoot(): ModuleWithProviders {
        return {
            ngModule: MaterialModule,
        };
    }
}
