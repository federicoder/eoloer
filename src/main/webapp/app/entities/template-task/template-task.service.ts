import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { ITemplateTask } from 'app/shared/model/template-task.model';

type EntityResponseType = HttpResponse<ITemplateTask>;
type EntityArrayResponseType = HttpResponse<ITemplateTask[]>;

@Injectable({ providedIn: 'root' })
export class TemplateTaskService {
  public resourceUrl = SERVER_API_URL + 'api/template-tasks';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/template-tasks';

  constructor(protected http: HttpClient) {}

  create(templateTask: ITemplateTask): Observable<EntityResponseType> {
    return this.http.post<ITemplateTask>(this.resourceUrl, templateTask, { observe: 'response' });
  }

  update(templateTask: ITemplateTask): Observable<EntityResponseType> {
    return this.http.put<ITemplateTask>(this.resourceUrl, templateTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITemplateTask>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITemplateTask[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITemplateTask[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
