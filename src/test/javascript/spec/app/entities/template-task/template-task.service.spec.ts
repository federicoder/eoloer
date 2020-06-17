import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TemplateTaskService } from 'app/entities/template-task/template-task.service';
import { ITemplateTask, TemplateTask } from 'app/shared/model/template-task.model';

describe('Service Tests', () => {
  describe('TemplateTask Service', () => {
    let injector: TestBed;
    let service: TemplateTaskService;
    let httpMock: HttpTestingController;
    let elemDefault: ITemplateTask;
    let expectedResult: ITemplateTask | ITemplateTask[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TemplateTaskService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TemplateTask(0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TemplateTask', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TemplateTask()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TemplateTask', () => {
        const returnedFromService = Object.assign(
          {
            idTemplateTask: 1,
            ordineEsecuzione: 1,
            nome: 1,
            note: 1,
            pubPriv: 1,
            idTemplatePratica: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TemplateTask', () => {
        const returnedFromService = Object.assign(
          {
            idTemplateTask: 1,
            ordineEsecuzione: 1,
            nome: 1,
            note: 1,
            pubPriv: 1,
            idTemplatePratica: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TemplateTask', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
