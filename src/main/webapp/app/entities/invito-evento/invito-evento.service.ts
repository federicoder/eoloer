import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IInvitoEvento } from 'app/shared/model/invito-evento.model';

type EntityResponseType = HttpResponse<IInvitoEvento>;
type EntityArrayResponseType = HttpResponse<IInvitoEvento[]>;

@Injectable({ providedIn: 'root' })
export class InvitoEventoService {
  public resourceUrl = SERVER_API_URL + 'api/invito-eventos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/invito-eventos';

  constructor(protected http: HttpClient) {}

  create(invitoEvento: IInvitoEvento): Observable<EntityResponseType> {
    return this.http.post<IInvitoEvento>(this.resourceUrl, invitoEvento, { observe: 'response' });
  }

  update(invitoEvento: IInvitoEvento): Observable<EntityResponseType> {
    return this.http.put<IInvitoEvento>(this.resourceUrl, invitoEvento, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInvitoEvento>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitoEvento[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInvitoEvento[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
