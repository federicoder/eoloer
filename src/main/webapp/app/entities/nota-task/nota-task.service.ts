import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { INotaTask } from 'app/shared/model/nota-task.model';

type EntityResponseType = HttpResponse<INotaTask>;
type EntityArrayResponseType = HttpResponse<INotaTask[]>;

@Injectable({ providedIn: 'root' })
export class NotaTaskService {
  public resourceUrl = SERVER_API_URL + 'api/nota-tasks';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/nota-tasks';

  constructor(protected http: HttpClient) {}

  create(notaTask: INotaTask): Observable<EntityResponseType> {
    return this.http.post<INotaTask>(this.resourceUrl, notaTask, { observe: 'response' });
  }

  update(notaTask: INotaTask): Observable<EntityResponseType> {
    return this.http.put<INotaTask>(this.resourceUrl, notaTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INotaTask>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotaTask[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INotaTask[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
