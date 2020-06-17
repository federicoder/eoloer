import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoAttivitaComponent } from 'app/entities/invito-attivita/invito-attivita.component';
import { InvitoAttivitaService } from 'app/entities/invito-attivita/invito-attivita.service';
import { InvitoAttivita } from 'app/shared/model/invito-attivita.model';

describe('Component Tests', () => {
  describe('InvitoAttivita Management Component', () => {
    let comp: InvitoAttivitaComponent;
    let fixture: ComponentFixture<InvitoAttivitaComponent>;
    let service: InvitoAttivitaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoAttivitaComponent],
      })
        .overrideTemplate(InvitoAttivitaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoAttivitaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoAttivitaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvitoAttivita(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invitoAttivitas && comp.invitoAttivitas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
