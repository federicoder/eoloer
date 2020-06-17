import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IRisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';

type EntityResponseType = HttpResponse<IRisorseDisponibili>;
type EntityArrayResponseType = HttpResponse<IRisorseDisponibili[]>;

@Injectable({ providedIn: 'root' })
export class RisorseDisponibiliService {
  public resourceUrl = SERVER_API_URL + 'api/risorse-disponibilis';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/risorse-disponibilis';

  constructor(protected http: HttpClient) {}

  create(risorseDisponibili: IRisorseDisponibili): Observable<EntityResponseType> {
    return this.http.post<IRisorseDisponibili>(this.resourceUrl, risorseDisponibili, { observe: 'response' });
  }

  update(risorseDisponibili: IRisorseDisponibili): Observable<EntityResponseType> {
    return this.http.put<IRisorseDisponibili>(this.resourceUrl, risorseDisponibili, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRisorseDisponibili>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRisorseDisponibili[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRisorseDisponibili[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
