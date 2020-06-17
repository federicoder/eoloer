import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { INotaPratica } from 'app/shared/model/nota-pratica.model';

type EntityResponseType = HttpResponse<INotaPratica>;
type EntityArrayResponseType = HttpResponse<INotaPratica[]>;

@Injectable({ providedIn: 'root' })
export class NotaPraticaService {
  public resourceUrl = SERVER_API_URL + 'api/nota-praticas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/nota-praticas';

  constructor(protected http: HttpClient) {}

  create(notaPratica: INotaPratica): Observable<EntityResponseType> {
    return this.http.post<INotaPratica>(this.resourceUrl, notaPratica, { observe: 'response' });
  }

  update(notaPratica: INotaPratica): Observable<EntityResponseType> {
    return this.http.put<INotaPratica>(this.resourceUrl, notaPratica, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotaPratica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotaPratica[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotaPratica[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
