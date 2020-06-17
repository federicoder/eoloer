import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TipoAllegatoService } from 'app/entities/tipo-allegato/tipo-allegato.service';
import { ITipoAllegato, TipoAllegato } from 'app/shared/model/tipo-allegato.model';

describe('Service Tests', () => {
  describe('TipoAllegato Service', () => {
    let injector: TestBed;
    let service: TipoAllegatoService;
    let httpMock: HttpTestingController;
    let elemDefault: ITipoAllegato;
    let expectedResult: ITipoAllegato | ITipoAllegato[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TipoAllegatoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TipoAllegato(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TipoAllegato', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TipoAllegato()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TipoAllegato', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            formatiAmmessi: 'BBBBBB',
            maxDimensioneAmmessa: 'BBBBBB',
            version: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TipoAllegato', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            formatiAmmessi: 'BBBBBB',
            maxDimensioneAmmessa: 'BBBBBB',
            version: 'BBBBBB',
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

      it('should delete a TipoAllegato', () => {
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
