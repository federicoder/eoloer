import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IInvitato } from 'app/shared/model/invitato.model';

type EntityResponseType = HttpResponse<IInvitato>;
type EntityArrayResponseType = HttpResponse<IInvitato[]>;

@Injectable({ providedIn: 'root' })
export class InvitatoService {
  public resourceUrl = SERVER_API_URL + 'api/invitatoes';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/invitatoes';

  constructor(protected http: HttpClient) {}

  create(invitato: IInvitato): Observable<EntityResponseType> {
    return this.http.post<IInvitato>(this.resourceUrl, invitato, { observe: 'response' });
  }

  update(invitato: IInvitato): Observable<EntityResponseType> {
    return this.http.put<IInvitato>(this.resourceUrl, invitato, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInvitato>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitato[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitato[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
