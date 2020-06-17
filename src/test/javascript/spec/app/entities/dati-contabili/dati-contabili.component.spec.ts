import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { DatiContabiliComponent } from 'app/entities/dati-contabili/dati-contabili.component';
import { DatiContabiliService } from 'app/entities/dati-contabili/dati-contabili.service';
import { DatiContabili } from 'app/shared/model/dati-contabili.model';

describe('Component Tests', () => {
  describe('DatiContabili Management Component', () => {
    let comp: DatiContabiliComponent;
    let fixture: ComponentFixture<DatiContabiliComponent>;
    let service: DatiContabiliService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [DatiContabiliComponent],
      })
        .overrideTemplate(DatiContabiliComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DatiContabiliComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DatiContabiliService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DatiContabili(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.datiContabilis && comp.datiContabilis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
