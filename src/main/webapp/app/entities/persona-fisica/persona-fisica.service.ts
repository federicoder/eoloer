import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IPersonaFisica } from 'app/shared/model/persona-fisica.model';

type EntityResponseType = HttpResponse<IPersonaFisica>;
type EntityArrayResponseType = HttpResponse<IPersonaFisica[]>;

@Injectable({ providedIn: 'root' })
export class PersonaFisicaService {
  public resourceUrl = SERVER_API_URL + 'api/persona-fisicas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/persona-fisicas';

  constructor(protected http: HttpClient) {}

  create(personaFisica: IPersonaFisica): Observable<EntityResponseType> {
    return this.http.post<IPersonaFisica>(this.resourceUrl, personaFisica, { observe: 'response' });
  }

  update(personaFisica: IPersonaFisica): Observable<EntityResponseType> {
    return this.http.put<IPersonaFisica>(this.resourceUrl, personaFisica, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPersonaFisica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonaFisica[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPersonaFisica[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
