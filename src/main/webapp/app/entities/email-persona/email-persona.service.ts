import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IEmailPersona } from 'app/shared/model/email-persona.model';

type EntityResponseType = HttpResponse<IEmailPersona>;
type EntityArrayResponseType = HttpResponse<IEmailPersona[]>;

@Injectable({ providedIn: 'root' })
export class EmailPersonaService {
  public resourceUrl = SERVER_API_URL + 'api/email-personas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/email-personas';

  constructor(protected http: HttpClient) {}

  create(emailPersona: IEmailPersona): Observable<EntityResponseType> {
    return this.http.post<IEmailPersona>(this.resourceUrl, emailPersona, { observe: 'response' });
  }

  update(emailPersona: IEmailPersona): Observable<EntityResponseType> {
    return this.http.put<IEmailPersona>(this.resourceUrl, emailPersona, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEmailPersona>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEmailPersona[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEmailPersona[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
