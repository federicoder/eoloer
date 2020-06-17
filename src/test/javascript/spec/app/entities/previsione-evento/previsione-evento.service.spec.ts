import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PrevisioneEventoService } from 'app/entities/previsione-evento/previsione-evento.service';
import { IPrevisioneEvento, PrevisioneEvento } from 'app/shared/model/previsione-evento.model';

describe('Service Tests', () => {
  describe('PrevisioneEvento Service', () => {
    let injector: TestBed;
    let service: PrevisioneEventoService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrevisioneEvento;
    let expectedResult: IPrevisioneEvento | IPrevisioneEvento[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PrevisioneEventoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PrevisioneEvento(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PrevisioneEvento', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PrevisioneEvento()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PrevisioneEvento', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            dataInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            luogo: 'BBBBBB',
            indicazioniLuogo: 'BBBBBB',
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

      it('should return a list of PrevisioneEvento', () => {
        const returnedFromService = Object.assign(
          {
            idTaskRef: 1,
            dataInizio: 'BBBBBB',
            dataFine: 'BBBBBB',
            luogo: 'BBBBBB',
            indicazioniLuogo: 'BBBBBB',
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

      it('should delete a PrevisioneEvento', () => {
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
