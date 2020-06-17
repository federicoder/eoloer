import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PrevisioneAttivitaService } from 'app/entities/previsione-attivita/previsione-attivita.service';
import { IPrevisioneAttivita, PrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';

describe('Service Tests', () => {
  describe('PrevisioneAttivita Service', () => {
    let injector: TestBed;
    let service: PrevisioneAttivitaService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrevisioneAttivita;
    let expectedResult: IPrevisioneAttivita | IPrevisioneAttivita[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PrevisioneAttivitaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PrevisioneAttivita(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PrevisioneAttivita', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PrevisioneAttivita()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PrevisioneAttivita', () => {
        const returnedFromService = Object.assign(
          {
            idTask: 1,
            dataPianificata: 'BBBBBB',
            oraPianificata: 'BBBBBB',
            dataScadenza: 'BBBBBB',
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

      it('should return a list of PrevisioneAttivita', () => {
        const returnedFromService = Object.assign(
          {
            idTask: 1,
            dataPianificata: 'BBBBBB',
            oraPianificata: 'BBBBBB',
            dataScadenza: 'BBBBBB',
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

      it('should delete a PrevisioneAttivita', () => {
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
