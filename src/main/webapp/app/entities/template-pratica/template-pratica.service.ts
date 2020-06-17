import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ITemplatePratica } from 'app/shared/model/template-pratica.model';

type EntityResponseType = HttpResponse<ITemplatePratica>;
type EntityArrayResponseType = HttpResponse<ITemplatePratica[]>;

@Injectable({ providedIn: 'root' })
export class TemplatePraticaService {
  public resourceUrl = SERVER_API_URL + 'api/template-praticas';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/template-praticas';

  constructor(protected http: HttpClient) {}

  create(templatePratica: ITemplatePratica): Observable<EntityResponseType> {
    return this.http.post<ITemplatePratica>(this.resourceUrl, templatePratica, { observe: 'response' });
  }

  update(templatePratica: ITemplatePratica): Observable<EntityResponseType> {
    return this.http.put<ITemplatePratica>(this.resourceUrl, templatePratica, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITemplatePratica>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITemplatePratica[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITemplatePratica[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
