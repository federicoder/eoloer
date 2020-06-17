import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ITipoAllegato } from 'app/shared/model/tipo-allegato.model';

type EntityResponseType = HttpResponse<ITipoAllegato>;
type EntityArrayResponseType = HttpResponse<ITipoAllegato[]>;

@Injectable({ providedIn: 'root' })
export class TipoAllegatoService {
  public resourceUrl = SERVER_API_URL + 'api/tipo-allegatoes';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/tipo-allegatoes';

  constructor(protected http: HttpClient) {}

  create(tipoAllegato: ITipoAllegato): Observable<EntityResponseType> {
    return this.http.post<ITipoAllegato>(this.resourceUrl, tipoAllegato, { observe: 'response' });
  }

  update(tipoAllegato: ITipoAllegato): Observable<EntityResponseType> {
    return this.http.put<ITipoAllegato>(this.resourceUrl, tipoAllegato, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITipoAllegato>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoAllegato[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITipoAllegato[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
