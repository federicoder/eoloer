import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IRuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';

type EntityResponseType = HttpResponse<IRuoloOrganizzazione>;
type EntityArrayResponseType = HttpResponse<IRuoloOrganizzazione[]>;

@Injectable({ providedIn: 'root' })
export class RuoloOrganizzazioneService {
  public resourceUrl = SERVER_API_URL + 'api/ruolo-organizzaziones';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/ruolo-organizzaziones';

  constructor(protected http: HttpClient) {}

  create(ruoloOrganizzazione: IRuoloOrganizzazione): Observable<EntityResponseType> {
    return this.http.post<IRuoloOrganizzazione>(this.resourceUrl, ruoloOrganizzazione, { observe: 'response' });
  }

  update(ruoloOrganizzazione: IRuoloOrganizzazione): Observable<EntityResponseType> {
    return this.http.put<IRuoloOrganizzazione>(this.resourceUrl, ruoloOrganizzazione, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRuoloOrganizzazione>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRuoloOrganizzazione[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRuoloOrganizzazione[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
