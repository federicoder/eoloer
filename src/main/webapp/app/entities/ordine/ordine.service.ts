import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IOrdine } from 'app/shared/model/ordine.model';

type EntityResponseType = HttpResponse<IOrdine>;
type EntityArrayResponseType = HttpResponse<IOrdine[]>;

@Injectable({ providedIn: 'root' })
export class OrdineService {
  public resourceUrl = SERVER_API_URL + 'api/ordines';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/ordines';

  constructor(protected http: HttpClient) {}

  create(ordine: IOrdine): Observable<EntityResponseType> {
    return this.http.post<IOrdine>(this.resourceUrl, ordine, { observe: 'response' });
  }

  update(ordine: IOrdine): Observable<EntityResponseType> {
    return this.http.put<IOrdine>(this.resourceUrl, ordine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrdine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrdine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrdine[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
