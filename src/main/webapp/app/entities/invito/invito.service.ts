import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IInvito } from 'app/shared/model/invito.model';

type EntityResponseType = HttpResponse<IInvito>;
type EntityArrayResponseType = HttpResponse<IInvito[]>;

@Injectable({ providedIn: 'root' })
export class InvitoService {
  public resourceUrl = SERVER_API_URL + 'api/invitos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/invitos';

  constructor(protected http: HttpClient) {}

  create(invito: IInvito): Observable<EntityResponseType> {
    return this.http.post<IInvito>(this.resourceUrl, invito, { observe: 'response' });
  }

  update(invito: IInvito): Observable<EntityResponseType> {
    return this.http.put<IInvito>(this.resourceUrl, invito, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInvito>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvito[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvito[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
