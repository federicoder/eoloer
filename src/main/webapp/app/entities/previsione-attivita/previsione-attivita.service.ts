import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IPrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';

type EntityResponseType = HttpResponse<IPrevisioneAttivita>;
type EntityArrayResponseType = HttpResponse<IPrevisioneAttivita[]>;

@Injectable({ providedIn: 'root' })
export class PrevisioneAttivitaService {
  public resourceUrl = SERVER_API_URL + 'api/previsione-attivitas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/previsione-attivitas';

  constructor(protected http: HttpClient) {}

  create(previsioneAttivita: IPrevisioneAttivita): Observable<EntityResponseType> {
    return this.http.post<IPrevisioneAttivita>(this.resourceUrl, previsioneAttivita, { observe: 'response' });
  }

  update(previsioneAttivita: IPrevisioneAttivita): Observable<EntityResponseType> {
    return this.http.put<IPrevisioneAttivita>(this.resourceUrl, previsioneAttivita, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrevisioneAttivita>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisioneAttivita[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisioneAttivita[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
