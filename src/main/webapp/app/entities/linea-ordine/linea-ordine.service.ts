import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ILineaOrdine } from 'app/shared/model/linea-ordine.model';

type EntityResponseType = HttpResponse<ILineaOrdine>;
type EntityArrayResponseType = HttpResponse<ILineaOrdine[]>;

@Injectable({ providedIn: 'root' })
export class LineaOrdineService {
  public resourceUrl = SERVER_API_URL + 'api/linea-ordines';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/linea-ordines';

  constructor(protected http: HttpClient) {}

  create(lineaOrdine: ILineaOrdine): Observable<EntityResponseType> {
    return this.http.post<ILineaOrdine>(this.resourceUrl, lineaOrdine, { observe: 'response' });
  }

  update(lineaOrdine: ILineaOrdine): Observable<EntityResponseType> {
    return this.http.put<ILineaOrdine>(this.resourceUrl, lineaOrdine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILineaOrdine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILineaOrdine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILineaOrdine[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
