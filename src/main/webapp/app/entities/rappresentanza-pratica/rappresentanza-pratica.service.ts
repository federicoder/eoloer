import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IRappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';

type EntityResponseType = HttpResponse<IRappresentanzaPratica>;
type EntityArrayResponseType = HttpResponse<IRappresentanzaPratica[]>;

@Injectable({ providedIn: 'root' })
export class RappresentanzaPraticaService {
  public resourceUrl = SERVER_API_URL + 'api/rappresentanza-praticas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/rappresentanza-praticas';

  constructor(protected http: HttpClient) {}

  create(rappresentanzaPratica: IRappresentanzaPratica): Observable<EntityResponseType> {
    return this.http.post<IRappresentanzaPratica>(this.resourceUrl, rappresentanzaPratica, { observe: 'response' });
  }

  update(rappresentanzaPratica: IRappresentanzaPratica): Observable<EntityResponseType> {
    return this.http.put<IRappresentanzaPratica>(this.resourceUrl, rappresentanzaPratica, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRappresentanzaPratica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRappresentanzaPratica[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRappresentanzaPratica[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
