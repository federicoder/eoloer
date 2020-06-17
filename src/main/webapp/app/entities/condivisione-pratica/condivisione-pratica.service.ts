import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ICondivisionePratica } from 'app/shared/model/condivisione-pratica.model';

type EntityResponseType = HttpResponse<ICondivisionePratica>;
type EntityArrayResponseType = HttpResponse<ICondivisionePratica[]>;

@Injectable({ providedIn: 'root' })
export class CondivisionePraticaService {
  public resourceUrl = SERVER_API_URL + 'api/condivisione-praticas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/condivisione-praticas';

  constructor(protected http: HttpClient) {}

  create(condivisionePratica: ICondivisionePratica): Observable<EntityResponseType> {
    return this.http.post<ICondivisionePratica>(this.resourceUrl, condivisionePratica, { observe: 'response' });
  }

  update(condivisionePratica: ICondivisionePratica): Observable<EntityResponseType> {
    return this.http.put<ICondivisionePratica>(this.resourceUrl, condivisionePratica, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICondivisionePratica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICondivisionePratica[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICondivisionePratica[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
