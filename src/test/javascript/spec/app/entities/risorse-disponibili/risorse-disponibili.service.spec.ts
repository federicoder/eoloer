import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RisorseDisponibiliService } from 'app/entities/risorse-disponibili/risorse-disponibili.service';
import { IRisorseDisponibili, RisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';

describe('Service Tests', () => {
  describe('RisorseDisponibili Service', () => {
    let injector: TestBed;
    let service: RisorseDisponibiliService;
    let httpMock: HttpTestingController;
    let elemDefault: IRisorseDisponibili;
    let expectedResult: IRisorseDisponibili | IRisorseDisponibili[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RisorseDisponibiliService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new RisorseDisponibili(0, 0, 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a RisorseDisponibili', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new RisorseDisponibili()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a RisorseDisponibili', () => {
        const returnedFromService = Object.assign(
          {
            idStudioProfessionaleRef: 1,
            dataAttivazioneLicenza: 'BBBBBB',
            nrLicenza: 1,
            storageTotale: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of RisorseDisponibili', () => {
        const returnedFromService = Object.assign(
          {
            idStudioProfessionaleRef: 1,
            dataAttivazioneLicenza: 'BBBBBB',
            nrLicenza: 1,
            storageTotale: 1,
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

      it('should delete a RisorseDisponibili', () => {
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
