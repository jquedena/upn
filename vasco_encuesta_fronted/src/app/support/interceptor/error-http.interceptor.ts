import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/observable/throw'
import 'rxjs/add/operator/catch';

@Injectable()
export class ErrorHttpInterceptor implements HttpInterceptor {

    constructor() {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const started = Date.now();
        console.log("intercepted request ... ");
        // Clone the request to add the new header.
        const authReq = req.clone({ headers: req.headers.set("headerName", "headerValue")});
        console.log("Sending request with new header now ...");
        // send the newly created request
        return next
            .handle(authReq)
            .do(event => {
                if (event instanceof HttpResponse) {
                    const elapsed = Date.now() - started;
                    console.log(`Request for ${req.urlWithParams} took ${elapsed} ms.`);
                }
            })
            .catch((error, caught) => {
                // intercept the respons error and displace it to the console
                console.log("Error Occurred");
                console.log(error);
                // return the error to the method that called it
                return Observable.throw(error);
            }) as any;
    }
}