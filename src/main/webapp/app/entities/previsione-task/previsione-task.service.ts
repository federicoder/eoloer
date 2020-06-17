import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IPrevisioneTask } from 'app/shared/model/previsione-task.model';

type EntityResponseType = HttpResponse<IPrevisioneTask>;
type EntityArrayResponseType = HttpResponse<IPrevisioneTask[]>;

@Injectable({ providedIn: 'root' })
export class PrevisioneTaskService {
  public resourceUrl = SERVER_API_URL + 'api/previsione-tasks';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/previsione-tasks';

  constructor(protected http: HttpClient) {}

  create(previsioneTask: IPrevisioneTask): Observable<EntityResponseType> {
    return this.http.post<IPrevisioneTask>(this.resourceUrl, previsioneTask, { observe: 'response' });
  }

  update(previsioneTask: IPrevisioneTask): Observable<EntityResponseType> {
    return this.http.put<IPrevisioneTask>(this.resourceUrl, previsioneTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrevisioneTask>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisioneTask[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisioneTask[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
