import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ITagPersona } from 'app/shared/model/tag-persona.model';

type EntityResponseType = HttpResponse<ITagPersona>;
type EntityArrayResponseType = HttpResponse<ITagPersona[]>;

@Injectable({ providedIn: 'root' })
export class TagPersonaService {
  public resourceUrl = SERVER_API_URL + 'api/tag-personas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/tag-personas';

  constructor(protected http: HttpClient) {}

  create(tagPersona: ITagPersona): Observable<EntityResponseType> {
    return this.http.post<ITagPersona>(this.resourceUrl, tagPersona, { observe: 'response' });
  }

  update(tagPersona: ITagPersona): Observable<EntityResponseType> {
    return this.http.put<ITagPersona>(this.resourceUrl, tagPersona, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITagPersona>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagPersona[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITagPersona[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
