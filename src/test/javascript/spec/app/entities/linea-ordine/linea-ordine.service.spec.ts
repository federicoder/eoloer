import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { LineaOrdineService } from 'app/entities/linea-ordine/linea-ordine.service';
import { ILineaOrdine, LineaOrdine } from 'app/shared/model/linea-ordine.model';

describe('Service Tests', () => {
  describe('LineaOrdine Service', () => {
    let injector: TestBed;
    let service: LineaOrdineService;
    let httpMock: HttpTestingController;
    let elemDefault: ILineaOrdine;
    let expectedResult: ILineaOrdine | ILineaOrdine[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(LineaOrdineService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new LineaOrdine(0, 0, 0, 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a LineaOrdine', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new LineaOrdine()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a LineaOrdine', () => {
        const returnedFromService = Object.assign(
          {
            idOrdine: 1,
            idProdotto: 1,
            quantita: 1,
            importo: 1,
            codIva: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of LineaOrdine', () => {
        const returnedFromService = Object.assign(
          {
            idOrdine: 1,
            idProdotto: 1,
            quantita: 1,
            importo: 1,
            codIva: 'BBBBBB',
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

      it('should delete a LineaOrdine', () => {
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
