import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IConsuntivoTask } from 'app/shared/model/consuntivo-task.model';

type EntityResponseType = HttpResponse<IConsuntivoTask>;
type EntityArrayResponseType = HttpResponse<IConsuntivoTask[]>;

@Injectable({ providedIn: 'root' })
export class ConsuntivoTaskService {
  public resourceUrl = SERVER_API_URL + 'api/consuntivo-tasks';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/consuntivo-tasks';

  constructor(protected http: HttpClient) {}

  create(consuntivoTask: IConsuntivoTask): Observable<EntityResponseType> {
    return this.http.post<IConsuntivoTask>(this.resourceUrl, consuntivoTask, { observe: 'response' });
  }

  update(consuntivoTask: IConsuntivoTask): Observable<EntityResponseType> {
    return this.http.put<IConsuntivoTask>(this.resourceUrl, consuntivoTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IConsuntivoTask>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConsuntivoTask[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IConsuntivoTask[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
