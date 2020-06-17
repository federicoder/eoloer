import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CondivisionePraticaService } from 'app/entities/condivisione-pratica/condivisione-pratica.service';
import { ICondivisionePratica, CondivisionePratica } from 'app/shared/model/condivisione-pratica.model';

describe('Service Tests', () => {
  describe('CondivisionePratica Service', () => {
    let injector: TestBed;
    let service: CondivisionePraticaService;
    let httpMock: HttpTestingController;
    let elemDefault: ICondivisionePratica;
    let expectedResult: ICondivisionePratica | ICondivisionePratica[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CondivisionePraticaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CondivisionePratica(0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CondivisionePratica', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CondivisionePratica()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CondivisionePratica', () => {
        const returnedFromService = Object.assign(
          {
            idUserAmmesso: 1,
            ruolo: 1,
            idUserConcedente: 1,
            statoInvito: 1,
            idPratica: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CondivisionePratica', () => {
        const returnedFromService = Object.assign(
          {
            idUserAmmesso: 1,
            ruolo: 1,
            idUserConcedente: 1,
            statoInvito: 1,
            idPratica: 1,
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

      it('should delete a CondivisionePratica', () => {
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
