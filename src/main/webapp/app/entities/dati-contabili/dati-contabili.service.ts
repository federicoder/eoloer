import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IDatiContabili } from 'app/shared/model/dati-contabili.model';

type EntityResponseType = HttpResponse<IDatiContabili>;
type EntityArrayResponseType = HttpResponse<IDatiContabili[]>;

@Injectable({ providedIn: 'root' })
export class DatiContabiliService {
  public resourceUrl = SERVER_API_URL + 'api/dati-contabilis';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/dati-contabilis';

  constructor(protected http: HttpClient) {}

  create(datiContabili: IDatiContabili): Observable<EntityResponseType> {
    return this.http.post<IDatiContabili>(this.resourceUrl, datiContabili, { observe: 'response' });
  }

  update(datiContabili: IDatiContabili): Observable<EntityResponseType> {
    return this.http.put<IDatiContabili>(this.resourceUrl, datiContabili, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDatiContabili>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDatiContabili[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDatiContabili[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
