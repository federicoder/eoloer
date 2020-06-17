import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { InvitoEventoComponent } from 'app/entities/invito-evento/invito-evento.component';
import { InvitoEventoService } from 'app/entities/invito-evento/invito-evento.service';
import { InvitoEvento } from 'app/shared/model/invito-evento.model';

describe('Component Tests', () => {
  describe('InvitoEvento Management Component', () => {
    let comp: InvitoEventoComponent;
    let fixture: ComponentFixture<InvitoEventoComponent>;
    let service: InvitoEventoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitoEventoComponent],
      })
        .overrideTemplate(InvitoEventoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitoEventoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitoEventoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InvitoEvento(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.invitoEventos && comp.invitoEventos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
