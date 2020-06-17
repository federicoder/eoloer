import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, Search } from 'app/shared/util/request-util';
import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';

type EntityResponseType = HttpResponse<IStudioProfessionale>;
type EntityArrayResponseType = HttpResponse<IStudioProfessionale[]>;

@Injectable({ providedIn: 'root' })
export class StudioProfessionaleService {
  public resourceUrl = SERVER_API_URL + 'api/studio-professionales';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/studio-professionales';

  constructor(protected http: HttpClient) {}

  create(studioProfessionale: IStudioProfessionale): Observable<EntityResponseType> {
    return this.http.post<IStudioProfessionale>(this.resourceUrl, studioProfessionale, { observe: 'response' });
  }

  update(studioProfessionale: IStudioProfessionale): Observable<EntityResponseType> {
    return this.http.put<IStudioProfessionale>(this.resourceUrl, studioProfessionale, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStudioProfessionale>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStudioProfessionale[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: Search): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStudioProfessionale[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
