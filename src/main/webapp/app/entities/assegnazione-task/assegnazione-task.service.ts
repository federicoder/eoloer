import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';

type EntityResponseType = HttpResponse<IAssegnazioneTask>;
type EntityArrayResponseType = HttpResponse<IAssegnazioneTask[]>;

@Injectable({ providedIn: 'root' })
export class AssegnazioneTaskService {
  public resourceUrl = SERVER_API_URL + 'api/assegnazione-tasks';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/assegnazione-tasks';

  constructor(protected http: HttpClient) {}

  create(assegnazioneTask: IAssegnazioneTask): Observable<EntityResponseType> {
    return this.http.post<IAssegnazioneTask>(this.resourceUrl, assegnazioneTask, { observe: 'response' });
  }

  update(assegnazioneTask: IAssegnazioneTask): Observable<EntityResponseType> {
    return this.http.put<IAssegnazioneTask>(this.resourceUrl, assegnazioneTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAssegnazioneTask>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAssegnazioneTask[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAssegnazioneTask[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
