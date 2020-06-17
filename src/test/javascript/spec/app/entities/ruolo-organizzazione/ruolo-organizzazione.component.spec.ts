import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { RuoloOrganizzazioneComponent } from 'app/entities/ruolo-organizzazione/ruolo-organizzazione.component';
import { RuoloOrganizzazioneService } from 'app/entities/ruolo-organizzazione/ruolo-organizzazione.service';
import { RuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';

describe('Component Tests', () => {
  describe('RuoloOrganizzazione Management Component', () => {
    let comp: RuoloOrganizzazioneComponent;
    let fixture: ComponentFixture<RuoloOrganizzazioneComponent>;
    let service: RuoloOrganizzazioneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RuoloOrganizzazioneComponent],
      })
        .overrideTemplate(RuoloOrganizzazioneComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RuoloOrganizzazioneComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RuoloOrganizzazioneService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RuoloOrganizzazione(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ruoloOrganizzaziones && comp.ruoloOrganizzaziones[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
