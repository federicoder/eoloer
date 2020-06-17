import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IUserPersona } from 'app/shared/model/user-persona.model';

type EntityResponseType = HttpResponse<IUserPersona>;
type EntityArrayResponseType = HttpResponse<IUserPersona[]>;

@Injectable({ providedIn: 'root' })
export class UserPersonaService {
  public resourceUrl = SERVER_API_URL + 'api/user-personas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/user-personas';

  constructor(protected http: HttpClient) {}

  create(userPersona: IUserPersona): Observable<EntityResponseType> {
    return this.http.post<IUserPersona>(this.resourceUrl, userPersona, { observe: 'response' });
  }

  update(userPersona: IUserPersona): Observable<EntityResponseType> {
    return this.http.put<IUserPersona>(this.resourceUrl, userPersona, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUserPersona>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserPersona[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUserPersona[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
