import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ITelefonoPersona } from 'app/shared/model/telefono-persona.model';

type EntityResponseType = HttpResponse<ITelefonoPersona>;
type EntityArrayResponseType = HttpResponse<ITelefonoPersona[]>;

@Injectable({ providedIn: 'root' })
export class TelefonoPersonaService {
  public resourceUrl = SERVER_API_URL + 'api/telefono-personas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/telefono-personas';

  constructor(protected http: HttpClient) {}

  create(telefonoPersona: ITelefonoPersona): Observable<EntityResponseType> {
    return this.http.post<ITelefonoPersona>(this.resourceUrl, telefonoPersona, { observe: 'response' });
  }

  update(telefonoPersona: ITelefonoPersona): Observable<EntityResponseType> {
    return this.http.put<ITelefonoPersona>(this.resourceUrl, telefonoPersona, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITelefonoPersona>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITelefonoPersona[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITelefonoPersona[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
