import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

type EntityResponseType = HttpResponse<IAllegatoTemplateTask>;
type EntityArrayResponseType = HttpResponse<IAllegatoTemplateTask[]>;

@Injectable({ providedIn: 'root' })
export class AllegatoTemplateTaskService {
  public resourceUrl = SERVER_API_URL + 'api/allegato-template-tasks';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/allegato-template-tasks';

  constructor(protected http: HttpClient) {}

  create(allegatoTemplateTask: IAllegatoTemplateTask): Observable<EntityResponseType> {
    return this.http.post<IAllegatoTemplateTask>(this.resourceUrl, allegatoTemplateTask, { observe: 'response' });
  }

  update(allegatoTemplateTask: IAllegatoTemplateTask): Observable<EntityResponseType> {
    return this.http.put<IAllegatoTemplateTask>(this.resourceUrl, allegatoTemplateTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAllegatoTemplateTask>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAllegatoTemplateTask[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAllegatoTemplateTask[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
