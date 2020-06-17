import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { INotePersona } from 'app/shared/model/note-persona.model';

type EntityResponseType = HttpResponse<INotePersona>;
type EntityArrayResponseType = HttpResponse<INotePersona[]>;

@Injectable({ providedIn: 'root' })
export class NotePersonaService {
  public resourceUrl = SERVER_API_URL + 'api/note-personas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/note-personas';

  constructor(protected http: HttpClient) {}

  create(notePersona: INotePersona): Observable<EntityResponseType> {
    return this.http.post<INotePersona>(this.resourceUrl, notePersona, { observe: 'response' });
  }

  update(notePersona: INotePersona): Observable<EntityResponseType> {
    return this.http.put<INotePersona>(this.resourceUrl, notePersona, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotePersona>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotePersona[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotePersona[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
