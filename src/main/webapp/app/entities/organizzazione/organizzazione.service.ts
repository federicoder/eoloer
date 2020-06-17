import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IOrganizzazione } from 'app/shared/model/organizzazione.model';

type EntityResponseType = HttpResponse<IOrganizzazione>;
type EntityArrayResponseType = HttpResponse<IOrganizzazione[]>;

@Injectable({ providedIn: 'root' })
export class OrganizzazioneService {
  public resourceUrl = SERVER_API_URL + 'api/organizzaziones';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/organizzaziones';

  constructor(protected http: HttpClient) {}

  create(organizzazione: IOrganizzazione): Observable<EntityResponseType> {
    return this.http.post<IOrganizzazione>(this.resourceUrl, organizzazione, { observe: 'response' });
  }

  update(organizzazione: IOrganizzazione): Observable<EntityResponseType> {
    return this.http.put<IOrganizzazione>(this.resourceUrl, organizzazione, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IOrganizzazione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrganizzazione[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IOrganizzazione[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
