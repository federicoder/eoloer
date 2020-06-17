import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IIndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';

type EntityResponseType = HttpResponse<IIndirizzoPersona>;
type EntityArrayResponseType = HttpResponse<IIndirizzoPersona[]>;

@Injectable({ providedIn: 'root' })
export class IndirizzoPersonaService {
  public resourceUrl = SERVER_API_URL + 'api/indirizzo-personas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/indirizzo-personas';

  constructor(protected http: HttpClient) {}

  create(indirizzoPersona: IIndirizzoPersona): Observable<EntityResponseType> {
    return this.http.post<IIndirizzoPersona>(this.resourceUrl, indirizzoPersona, { observe: 'response' });
  }

  update(indirizzoPersona: IIndirizzoPersona): Observable<EntityResponseType> {
    return this.http.put<IIndirizzoPersona>(this.resourceUrl, indirizzoPersona, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IIndirizzoPersona>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIndirizzoPersona[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IIndirizzoPersona[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
