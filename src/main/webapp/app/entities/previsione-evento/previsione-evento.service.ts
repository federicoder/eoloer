import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IPrevisioneEvento } from 'app/shared/model/previsione-evento.model';

type EntityResponseType = HttpResponse<IPrevisioneEvento>;
type EntityArrayResponseType = HttpResponse<IPrevisioneEvento[]>;

@Injectable({ providedIn: 'root' })
export class PrevisioneEventoService {
  public resourceUrl = SERVER_API_URL + 'api/previsione-eventos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/previsione-eventos';

  constructor(protected http: HttpClient) {}

  create(previsioneEvento: IPrevisioneEvento): Observable<EntityResponseType> {
    return this.http.post<IPrevisioneEvento>(this.resourceUrl, previsioneEvento, { observe: 'response' });
  }

  update(previsioneEvento: IPrevisioneEvento): Observable<EntityResponseType> {
    return this.http.put<IPrevisioneEvento>(this.resourceUrl, previsioneEvento, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrevisioneEvento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisioneEvento[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisioneEvento[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
