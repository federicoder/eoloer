import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IInvitoPratica } from 'app/shared/model/invito-pratica.model';

type EntityResponseType = HttpResponse<IInvitoPratica>;
type EntityArrayResponseType = HttpResponse<IInvitoPratica[]>;

@Injectable({ providedIn: 'root' })
export class InvitoPraticaService {
  public resourceUrl = SERVER_API_URL + 'api/invito-praticas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/invito-praticas';

  constructor(protected http: HttpClient) {}

  create(invitoPratica: IInvitoPratica): Observable<EntityResponseType> {
    return this.http.post<IInvitoPratica>(this.resourceUrl, invitoPratica, { observe: 'response' });
  }

  update(invitoPratica: IInvitoPratica): Observable<EntityResponseType> {
    return this.http.put<IInvitoPratica>(this.resourceUrl, invitoPratica, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInvitoPratica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitoPratica[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitoPratica[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
