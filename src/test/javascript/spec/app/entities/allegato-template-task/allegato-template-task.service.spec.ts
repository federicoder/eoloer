import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AllegatoTemplateTaskService } from 'app/entities/allegato-template-task/allegato-template-task.service';
import { IAllegatoTemplateTask, AllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

describe('Service Tests', () => {
  describe('AllegatoTemplateTask Service', () => {
    let injector: TestBed;
    let service: AllegatoTemplateTaskService;
    let httpMock: HttpTestingController;
    let elemDefault: IAllegatoTemplateTask;
    let expectedResult: IAllegatoTemplateTask | IAllegatoTemplateTask[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AllegatoTemplateTaskService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AllegatoTemplateTask(0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AllegatoTemplateTask', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AllegatoTemplateTask()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AllegatoTemplateTask', () => {
        const returnedFromService = Object.assign(
          {
            idTemplateTaskRef: 1,
            idTipoAllegatoRef: 1,
            formato: 1,
            idFileRef: 1,
            pubPriv: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AllegatoTemplateTask', () => {
        const returnedFromService = Object.assign(
          {
            idTemplateTaskRef: 1,
            idTipoAllegatoRef: 1,
            formato: 1,
            idFileRef: 1,
            pubPriv: 1,
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

      it('should delete a AllegatoTemplateTask', () => {
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
