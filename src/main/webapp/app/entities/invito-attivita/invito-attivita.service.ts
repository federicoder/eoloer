import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IInvitoAttivita } from 'app/shared/model/invito-attivita.model';

type EntityResponseType = HttpResponse<IInvitoAttivita>;
type EntityArrayResponseType = HttpResponse<IInvitoAttivita[]>;

@Injectable({ providedIn: 'root' })
export class InvitoAttivitaService {
  public resourceUrl = SERVER_API_URL + 'api/invito-attivitas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/invito-attivitas';

  constructor(protected http: HttpClient) {}

  create(invitoAttivita: IInvitoAttivita): Observable<EntityResponseType> {
    return this.http.post<IInvitoAttivita>(this.resourceUrl, invitoAttivita, { observe: 'response' });
  }

  update(invitoAttivita: IInvitoAttivita): Observable<EntityResponseType> {
    return this.http.put<IInvitoAttivita>(this.resourceUrl, invitoAttivita, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInvitoAttivita>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitoAttivita[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitoAttivita[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
